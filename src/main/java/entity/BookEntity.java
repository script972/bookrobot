package entity;

import javax.persistence.*;

@Entity
@Table(name = "book", schema = "botscrewtest")
public class BookEntity {
    private int id;
    private String title;
    private AutherEntity auther;

    public BookEntity(int id, String title, AutherEntity auther) {
        this.id = id;
        this.title = title;
        this.auther = auther;
    }

    public BookEntity(String title, AutherEntity auther) {
        this.title = title;
        this.auther = auther;
    }

    public BookEntity() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JoinColumn(name = "auther", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    public AutherEntity getAuther() {
        return auther;
    }

    public void setAuther(AutherEntity auther) {
        this.auther = auther;
    }

    @Override
    public String toString() {
        return "book "+auther +" \""+ title+"\"";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (auther != null ? !auther.equals(that.auther) : that.auther != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (auther != null ? auther.hashCode() : 0);
        return result;
    }
}
