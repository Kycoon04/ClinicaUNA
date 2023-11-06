package cr.ac.una.clinicaws.model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author jomav
 */
public class HistoryDto {

    private Integer htId;
    private LocalDate htDate;
    private LocalDate htDateFinal;
    private Short htSpaces;
    private String htIniworking;
    private String htFinisworking;
    private Doctor htDoctor;

    public HistoryDto() {
    }

    public HistoryDto(Historytime history) {
        this.htId = history.getHtId();
        this.htDate = history.getHtDate();
        this.htSpaces = history.getHtSpaces();
        this.htIniworking  = history.getHtIniworking();
        this.htFinisworking = history.getHtFinisworking();
        this.htDoctor = history.getHtDoctor();
        this.htDateFinal = history.getHtDateFinal();
    }

    public LocalDate getHtDateFinal() {
        return htDateFinal;
    }

    public void setHtDateFinal(LocalDate htDateFinal) {
        this.htDateFinal = htDateFinal;
    }

    public Integer getHtId() {
        return htId;
    }

    public void setHtId(Integer htId) {
        this.htId = htId;
    }

    public LocalDate getHtDate() {
        return htDate;
    }

    public void setHtDate(LocalDate htDate) {
        this.htDate = htDate;
    }

    public Short getHtSpaces() {
        return htSpaces;
    }

    public void setHtSpaces(Short htSpaces) {
        this.htSpaces = htSpaces;
    }

    public String getHtIniworking() {
        return htIniworking;
    }

    public void setHtIniworking(String htIniworking) {
        this.htIniworking = htIniworking;
    }

    public String getHtFinisworking() {
        return htFinisworking;
    }

    public void setHtFinisworking(String htFinisworking) {
        this.htFinisworking = htFinisworking;
    }

    public Doctor getHtDoctor() {
        return htDoctor;
    }

    public void setHtDoctor(Doctor htDoctor) {
        this.htDoctor = htDoctor;
    }

}
