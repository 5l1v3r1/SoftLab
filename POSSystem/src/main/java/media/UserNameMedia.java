package media;

/**
 * @author HikmatD
 */
public class UserNameMedia {

    String Id;
    String usrName;

    public UserNameMedia() {
    }

    public UserNameMedia(String Id) {
        this.Id = Id;
    }

    public UserNameMedia(String id, String usrName) {
        this.Id = id;
        this.usrName = usrName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

}
