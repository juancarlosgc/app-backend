package com.sisgpn.backend.api.controller;

import com.sisgpn.backend.api.model.Persona;
import com.sisgpn.backend.api.service.IPersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private IPersonaService personaService;

    @GetMapping("/vertodo")
    public List<Persona> vertodo(){
        return personaService.findAll();
    }

    @GetMapping("/vertodo/page/{page}")
    public Page<Persona> vertodo(@PathVariable Integer page){
        return personaService.findAll(PageRequest.of(page,5));
    }



    @GetMapping("/ver/{idPersona}")
    public ResponseEntity<?> buscarId(@PathVariable Long idPersona){
        Persona persona=null;
        Map<String, Object> response = new HashMap<>();
        try {
            persona= personaService.findById(idPersona);
        }catch(DataAccessException e){
            response.put("mensaje", "Error al consultar la Base de Datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (persona == null){
            response.put("mensaje", "Persona con el ID ".concat(idPersona.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@Valid @RequestBody  Persona persona, BindingResult result){

        Persona personaCreada= null;
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
            personaCreada = personaService.save(persona);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al guardar en la Base de Datos");
            response.put("errores", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Registro creado con éxito");
        response.put("persona", personaCreada);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @PutMapping("/editar/{idPersona}")
    public ResponseEntity<?> editar(@Valid @RequestBody Persona persona,BindingResult result, @PathVariable Long idPersona){
        Persona personaActual = personaService.findById(idPersona);
        Persona personaActualizada= null;

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()){
            List<String> errores =result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }


        if (personaActual == null){
            response.put("mensaje", "Error: No se puede editar. Persona con el ID ".concat(idPersona.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            personaActual.setCedula(persona.getCedula());
            personaActual.setApellidos(persona.getApellidos());
            personaActual.setNombres(persona.getNombres());
            personaActual.setFechaNacimiento(persona.getFechaNacimiento());
            personaActual.setTipoSangre(persona.getTipoSangre());
            personaActual.setCiudadNacimiento(persona.getCiudadNacimiento());
            personaActual.setTipoSangre(persona.getTipoSangre());
            personaActual.setRango(persona.getRango());
            personaActualizada=personaService.save(personaActual);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar registro en la Base de Datos");
            response.put("errores", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Registro actualizado con éxito");
        response.put("persona", personaActualizada);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED) ;
    }

    @DeleteMapping("/eliminar/{idPersona}")
    public ResponseEntity<?> eliminar(@PathVariable Long idPersona){
        Map<String, Object> response = new HashMap<>();
        try {
            personaService.delete(idPersona);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar registro en la Base de Datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK) ;

    }

}
