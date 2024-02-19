package com.sisgpn.backend.api.controller;


import com.sisgpn.backend.api.model.Distrito;
import com.sisgpn.backend.api.model.Persona;
import com.sisgpn.backend.api.service.IDistritoService;
import com.sisgpn.backend.api.service.IPersonaService;
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
@RequestMapping("/distritos")
public class DistritoController {

    @Autowired
    private IDistritoService distritoService;

    @GetMapping("/vertodo/page/{page}")
    public Page<Distrito> vertodo(@PathVariable Integer page){
        return distritoService.findAll(PageRequest.of(page,5));
    }

    @GetMapping("/ver/{idDistrito}")
    public ResponseEntity<?> buscarId(@PathVariable Long idDistrito){
        Distrito distrito=null;

        Map<String, Object> response = new HashMap<>();

        try {
            distrito= distritoService.findById(idDistrito);
        }catch(DataAccessException e){
            response.put("mensaje", "Error al consultar la Base de Datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (distrito == null){
            response.put("mensaje", "Distrito con el ID ".concat(idDistrito.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Distrito>(distrito, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@Valid @RequestBody  Distrito distrito, BindingResult result){

        Distrito distritoCreado= null;

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
            distritoCreado = distritoService.save(distrito);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al guardar en la Base de Datos");
            response.put("errores", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Registro creado con éxito");
        response.put("distrito", distritoCreado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{idDistrito}")
    public ResponseEntity<?> editar(@Valid @RequestBody Distrito distrito,BindingResult result, @PathVariable Long idDistrito){
        Distrito distritoActual = distritoService.findById(idDistrito);
        Distrito distritoActualizado= null;

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()){
            List<String> errores =result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }


        if (distritoActual == null){
            response.put("mensaje", "Error: No se puede editar. Persona con el ID ".concat(idDistrito.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            distritoActual.setCodigoDistrito(distrito.getCodigoDistrito());
            distritoActual.setNombreDistrito(distrito.getNombreDistrito());
            distritoActual.setParroquia(distrito.getParroquia());
            distritoActual.setProvincia(distrito.getProvincia());

            distritoActualizado=distritoService.save(distritoActual);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar registro en la Base de Datos");
            response.put("errores", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Registro actualizado con éxito");
        response.put("distrito", distritoActualizado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED) ;
    }

    @DeleteMapping("/eliminar/{idDistrito}")
    public ResponseEntity<?> eliminar(@PathVariable Long idDistrito){
        Map<String, Object> response = new HashMap<>();
        try {
            distritoService.delete(idDistrito);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar registro en la Base de Datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK) ;

    }
}
