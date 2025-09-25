package com.tesis.dto;

public class PersonaRecord {
    private final Long id;
    private final String cuit;
    private final String tipoPersona;
    
    public PersonaRecord(Long id, String cuit, String tipoPersona) {
        this.id = id;
        this.cuit = cuit;
        this.tipoPersona = tipoPersona;
    }
    
    public Long id() {
        return id;
    }
    
    public String cuit() {
        return cuit;
    }
    
    public String tipoPersona() {
        return tipoPersona;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PersonaRecord that = (PersonaRecord) obj;
        return java.util.Objects.equals(id, that.id) &&
               java.util.Objects.equals(cuit, that.cuit) &&
               java.util.Objects.equals(tipoPersona, that.tipoPersona);
    }
    
    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, cuit, tipoPersona);
    }
    
    @Override
    public String toString() {
        return "PersonaRecord{" +
                "id=" + id +
                ", cuit='" + cuit + '\'' +
                ", tipoPersona='" + tipoPersona + '\'' +
                '}';
    }
}