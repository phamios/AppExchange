package androidhive.info.materialdesign.model;

/**
 * Created by sonpx on 5/9/15.
 */
public class AppInstalled {

    private String id;
    private String cateid;
    private String icon;
    private String name;

    public AppInstalled(String id, String cateid, String icon, String name, String balance, String link) {
        this.id = id;
        this.cateid = cateid;
        this.icon = icon;
        this.name = name;
        this.balance = balance;
        this.link = link;
    }

    private String balance;

    public AppInstalled() {

    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCateid() {
        return cateid;
    }

    public void setCateid(String cateid) {
        this.cateid = cateid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    private String link;

}
