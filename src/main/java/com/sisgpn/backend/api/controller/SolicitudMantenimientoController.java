package com.sisgpn.backend.api.controller;

import com.sisgpn.backend.api.model.Persona;
import com.sisgpn.backend.api.model.SolicitudMantenimiento;
import com.sisgpn.backend.api.model.Vehiculo;
import com.sisgpn.backend.api.service.IPersonaService;
import com.sisgpn.backend.api.service.ISolicitudMantenimientoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/solicitudes")
public class SolicitudMantenimientoController {

    @Autowired
    private ISolicitudMantenimientoService solicitudMantenimientoService;

    @GetMapping("/vertodo/page/{page}")
    public Page<SolicitudMantenimiento> vertodo(@PathVariable Integer page){

        return solicitudMantenimientoService.findAll(PageRequest.of(page,5));
    }

    @GetMapping("/ver/{idSolicitud}")
    public ResponseEntity<?> buscarId(@PathVariable Long idSolicitud){
        SolicitudMantenimiento solicitud=null;
        Map<String, Object> response = new HashMap<>();
        try {
            solicitud= solicitudMantenimientoService.findById(idSolicitud);
        }catch(DataAccessException e){
            response.put("mensaje", "Error al consultar la Base de Datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (solicitud == null){
            response.put("mensaje", "Persona con el ID ".concat(idSolicitud.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SolicitudMantenimiento>(solicitud, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@Valid @RequestBody SolicitudMantenimiento solicitudMantenimiento, BindingResult result){

        SolicitudMantenimiento solicitudCreada= null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()){
            List<String> errores =result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            solicitudCreada = solicitudMantenimientoService.save(solicitudMantenimiento);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al guardar en la Base de Datos");
            response.put("errores", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Registro creado con éxito");
        response.put("solicitud", solicitudCreada);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/vehiculos")
    public List<Vehiculo> listarVehiculos(){
        return solicitudMantenimientoService.findAllVehiculos();
    }

    @GetMapping("/personas")
    public List<Persona> listarPersonas(){
        return solicitudMantenimientoService.findAllPersonas();
    }
}
