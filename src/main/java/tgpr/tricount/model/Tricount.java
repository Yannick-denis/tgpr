package tgpr.tricount.model;

import org.springframework.util.Assert;
import tgpr.framework.Model;
import tgpr.framework.Params;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import java.util.*;


import java.util.List;
import java.util.Objects;

public class Tricount extends Model {
    public enum Fields {
        Id, Title, Description, CreatedAt, Templates, Creator
    }

    public Tricount() {

    }

    /*public Tricount(int idTricount, String title, String destription, Template templates, int creator) {
    }

     */

    public Tricount(String title, int creatorId) {
        this.title = title;
        this.createdAt = LocalDateTime.now();
        this.creatorId = creatorId;
    }
    public Tricount(String title, String description, int creatorId) {
        this.title = title;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.creatorId = creatorId;
    }
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id =id;
    }
    public List<Operation> getOperations() {
        return queryList(Operation.class, "select * from operations where tricount=:id order by operation_date desc",
                new Params("id", id));
    }

//    private static List<Double> getAmoutForBalance(int iduser,int idTricount){
//        List<Double> list=new ArrayList<>();
//        return queryList(Class<Double>," SELECT SUM(weight)  FROM repartitions WHERE operation IN(SELECT id FROM operations o where  o.tricount=:id_tricount)\n" +
//                "    SELECT SUM(amount) FROM operations WHERE tricount = :id_tricount\n" +
//                "    SELECT SUM(weight)  FROM repartitions WHERE user = id_user and operation IN(SELECT id FROM operations o where o.tricount= :id_tricount)",
//                new Params("id_user",iduser).add("id_tricount",idTricount));
//    }
//    public  static double getBalance(int iduser,int idTricount){
//        double res=0;
//        List<Double> listRes = getAmoutForBalance(iduser,idTricount);
//        res=(listRes.get(1)/listRes.get(0))*listRes.get(2);
//
//        return res;
//    }


    public List<Subscription> getSubscriptions() {
        return queryList(Subscription.class, "select * from subscriptions s where tricount=:id",
                new Params("id", id));
    }
    public List<User> getParticipants() {
        return queryList(User.class, """
                        select u.* from subscriptions s
                        join users u on s.user=u.id
                        where tricount=:id
                        """,
                new Params("id", id));
    }
    public List<Template> getTemplates() {
        return queryList(Template.class, """
                        select * from templates
                        where tricount=:id
                        """,
                new Params("id", id));
    }
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    private LocalDateTime createdAt;
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    private int creatorId;
    public int getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }
    public User getCreator() {
        return User.getByKey(creatorId);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tricount tricount = (Tricount) o;
        return id == tricount.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "Tricount[" +
                "id=" + id +
                ", title=" + title +
                ", description=" + description +
                ", createdAt=" + createdAt +
                ", creatorId=" + creatorId +
                "]";
    }
    @Override
    protected void mapper(ResultSet rs) throws SQLException {
        id = rs.getInt("id");
        title = rs.getString("title");
        description = rs.getString("description");
        createdAt = rs.getObject("created_at", LocalDateTime.class);
        creatorId = rs.getInt("creator");
    }
    @Override
    public void reload() {
        reload("select * from tricounts where id=:id",
                new Params("id", id));
    }
    public static Tricount getByKey(int id) {
        return queryOne(Tricount.class, "select * from tricounts where id=:id",
                new Params("id", id));
    }
    public static Tricount getByTitleAndUser(String title, User user) {
        return queryOne(Tricount.class, "select * from tricounts where title=:title and creator=:user",
                new Params("title", title).add("user", user.getId()));
    }
    public static List<Tricount> getAll() {
        return queryList(Tricount.class, "SELECT * \n" +
                        "FROM tricounts t  \n" +
                        "WHERE t.id in (SELECT s.tricount\n" +
                        "              from subscriptions s \n" +
                        "              where s.user = :user)",
                new Params("user", Security.getLoggedUser().getId()));
    }
    public static List<Tricount> getPaginated(int start, int count) {
        return queryList(Tricount.class, "select * from tricounts limit :start, :count",
                new Params().add("start", start).add("count", count));
    }
    public Tricount save() {
        int c;
        Tricount obj = getByKey(id);
        String sql;
        var params = new Params()
                .add("id", id)
                .add("title", title)
                .add("description", description)
                .add("created_at", createdAt)
                .add("creator", creatorId);
        if (obj == null) {
            sql = "insert into tricounts (title,description,created_at,creator) " +
                    "values (:title,:description,:created_at,:creator)";
            int id = insert(sql, params);
            if (id > 0)
                this.id = id;
        } else {
            sql = "update tricounts set title=:title," +
                    "description=:description," +
                    "created_at=:created_at," +
                    "creator=:creator " +
                    "where id=:id";
            c = execute(sql, params);
            Assert.isTrue(c == 1, "Something went wrong");
        }
        return this;
    }
    public void delete() {
        int c = execute("delete from tricounts where id=:id",
                new Params("id", id));
        Assert.isTrue(c == 1, "Something went wrong");
    }
    public static List<Tricount> getFiltered(String filterText){
        Tricount.getAll();
        String filter = '%' + filterText + '%';
        Params params = new Params("filter", filter)
                .add("user", Security.getLoggedUser().getId());
//        String sql = "SELECT * FROM tricounts ";
        String sql = """
                SELECT *
                FROM tricounts t , users u
                where t.creator = u.id
                and (t.title like :filter or t.description like :filter or u.full_name like :filter)
                and t.id in (SELECT s.tricount
                             from subscriptions s
                             where s.user = :user)
                """;
        return queryList(Tricount.class,sql, params);
    }
    //return Tricount.getAll();
    public boolean tricountFilter(String filtre){
        String title = "";
        String description = "";
        String creator = "";
        for (int i = 0 ; i < Tricount.getAll().size() ;i++ ){
            if (title.length() < filtre.length() ||creator.length() < filtre.length() ){
                title += Tricount.getAll().get(i).getTitle().charAt(i);
                // creator += User.getNameByKey(Tricount.getAll().get(i).getCreatorId());
            }
            if (description.length() < filtre.length() && Tricount.getAll().get(i).getDescription() != null){
                description += Tricount.getAll().get(i).getDescription().charAt(i);
            }
        }
        if (title.equalsIgnoreCase(filtre) || description.equalsIgnoreCase(filtre ) || creator.equalsIgnoreCase(filtre)){
            return true;
        }
        return false;
//            int idx = 0;
//            for (int i = 0; i < Tricount.getAll().size() ; i++){
//            if (filtre.charAt(idx) == Tricount.getAll().get(i).getTitle().charAt(i)){
//                    idx++;
//                    if (idx == filtre.length())
//                        return true;
//                }else
//                    idx = 0;
//                return false;
//            }
    }
}