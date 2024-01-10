package demo.embedded;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AgenceId implements Serializable {

    private int code;
    private String libelle;

    public AgenceId() {
    }

    public AgenceId(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgenceId agenceId = (AgenceId) o;
        return code == agenceId.code && Objects.equals(libelle, agenceId.libelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, libelle);
    }
}
