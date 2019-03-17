package services.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @PKANE_NS
 */
@Entity
public class Anggota {
    private long id;
    private String nama;
    private String nim;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nama", nullable = false, length = 100)
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Basic
    @Column(name = "nim", nullable = false, length = 20)
    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Anggota anggota = (Anggota) o;

        if (id != anggota.id) return false;
        if (nama != null ? !nama.equals(anggota.nama) : anggota.nama != null) return false;
        if (nim != null ? !nim.equals(anggota.nim) : anggota.nim != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nama != null ? nama.hashCode() : 0);
        result = 31 * result + (nim != null ? nim.hashCode() : 0);
        return result;
    }
}