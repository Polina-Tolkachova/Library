package by.polina.library.entity;


/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * describes objects of Author table in the database
 */
public class Author {

    private int id;
    private String name;
    private String surname;

    public Author() {
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

    public void setSurname(String surname) {
        this.surname = surname;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Author author = (Author) o;

        if (id != author.id)
            return false;
        if (name != null ? !name.equals(author.name) : author.name != null)
            return false;
        if (surname != null ? !surname.equals(author.surname)
                : author.surname != null)
            return false;

        return true;
    }


    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Author{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
