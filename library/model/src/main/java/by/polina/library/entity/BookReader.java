package by.polina.library.entity;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */


/**
 * describes objects of Reader table in the database
 */
public class BookReader {

    private int id;
    private String name;
    private String surname;
    private String mail;
    private String password;
    private int role;                               // role (1 - admin, 2 - user)
    private int banned;                             // 0 - not banned

    public BookReader() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {this.surname = surname;}

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {return role;}

    public void setRole(int role) {
        this.role = role;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookReader)) return false;

        BookReader that = (BookReader) o;

        if (banned != that.banned) return false;
        if (id != that.id) return false;
        if (role != that.role) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;

        return true;
    }


    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + role;
        result = 31 * result + banned;
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookReader{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", mail='").append(mail).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append(", banned=").append(banned);
        sb.append('}');
        return sb.toString();
    }
}
