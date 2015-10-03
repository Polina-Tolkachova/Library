package by.polina.library.entity;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */


/**
 * describes objects of Category table in the database
 */
public class Category {

    private int id;
    private String name;

    public Category() {
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


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Category category = (Category) o;

        if (id != category.id)
            return false;
        if (name != null ? !name.equals(category.name) : category.name != null)
            return false;

        return true;
    }


    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Category{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
