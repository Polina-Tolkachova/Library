package by.polina.library.entity;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * describes objects of Book table in the database
 */
public class  Book {

    private int id;
    private String title;
    private int author;
    private int category;
    private int count;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (author != book.author) return false;
        if (category != book.category) return false;
        if (count != book.count) return false;
        if (id != book.id) return false;
        if (!title.equals(book.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + author;
        result = 31 * result + category;
        result = 31 * result + count;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", author=").append(author);
        sb.append(", category=").append(category);
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }
}