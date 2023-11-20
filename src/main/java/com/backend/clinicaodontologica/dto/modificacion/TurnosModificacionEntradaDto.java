package com.backend.clinicaodontologica.dto.modificacion;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnosModificacionEntradaDto {

    @NotNull(message = "Debe proveerse el id del turno que desea modificar")
    private Long id;

    @FutureOrPresent(message = "La fecha y hora no pueden ser anterior al día de hoy")
    @NotNull(message = "El campo fecha y hora no puede ser nulo")
    //@NotBlank(message = "El campo fecha y hora no puede estar en blanco")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaYHora;

    @NotNull(message = "El campo Odontologo no puede ser nulo")
    //@NotBlank(message = "El campo Odontologo no puede estar en blanco")
    @Valid
    private OdontologoEntradaDto odontologoEntradaDto;

    @NotNull(message = "El campo Paciente no puede ser nulo")
    //@NotBlank(message = "El campo Paciente no puede estar en blanco")
    @Valid
    private PacienteEntradaDto pacienteEntradaDto;

    public TurnosModificacionEntradaDto() {
    }

    public TurnosModificacionEntradaDto(Long id, LocalDateTime fechaYHora, OdontologoEntradaDto odontologoEntradaDto, PacienteEntradaDto pacienteEntradaDto) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.odontologoEntradaDto = odontologoEntradaDto;
        this.pacienteEntradaDto = pacienteEntradaDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public OdontologoEntradaDto getOdontologoEntradaDto() {
        return odontologoEntradaDto;
    }

    public void setOdontologoEntradaDto(OdontologoEntradaDto odontologoEntradaDto) {
        this.odontologoEntradaDto = odontologoEntradaDto;
    }

    public PacienteEntradaDto getPacienteEntradaDto() {
        return pacienteEntradaDto;
    }

    public void setPacienteEntradaDto(PacienteEntradaDto pacienteEntradaDto) {
        this.pacienteEntradaDto = pacienteEntradaDto;
    }
}
