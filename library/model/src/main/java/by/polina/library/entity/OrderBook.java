package by.polina.library.entity;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */


/**
 * describes objects of OrderBook table in the database
 */
public class OrderBook {

    private int id;
    private int idBook;
    private int idReader;
    private int status;   //0 - lending library  1 - reading hall

    public OrderBook() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdReader() {
        return idReader;
    }

    public void setIdReader(int idReader) {
        this.idReader = idReader;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderBook)) return false;

        OrderBook orderBook = (OrderBook) o;

        if (id != orderBook.id) return false;
        if (idBook != orderBook.idBook) return false;
        if (idReader != orderBook.idReader) return false;
        if (status != orderBook.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idBook;
        result = 31 * result + idReader;
        result = 31 * result + status;
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderBook{");
        sb.append("id=").append(id);
        sb.append(", idBook=").append(idBook);
        sb.append(", idReader=").append(idReader);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}