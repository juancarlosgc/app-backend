package com.sisgpn.backend.api.controller;

import com.sisgpn.backend.api.model.Mantenimiento;
import com.sisgpn.backend.api.model.Persona;
import com.sisgpn.backend.api.service.IMantenimientoService;
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
@RequestMapping("/mantenimientos")
public class MantenimientoController {

    @Autowired
    private IMantenimientoService mantenimientoService;

    @GetMapping("/vertodo")
    public List<Mantenimiento> vertodo(){
        return mantenimientoService.findAll();
    }

    @GetMapping("/vertodo/page/{page}")
    public Page<Mantenimiento> vertodo(@PathVariable Integer page){
        return mantenimientoService.findAll(PageRequest.of(page,5));
    }

    @GetMapping("/ver/{idMantenimiento}")
    public ResponseEntity<?> buscarId(@PathVariable Long idMantenimiento){
        Mantenimiento mantenimiento=null;
        Map<String, Object> response = new HashMap<>();
        try {
            mantenimiento= mantenimientoService.findById(idMantenimiento);
        }catch(DataAccessException e){
            response.put("mensaje", "Error al consultar la Base de Datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (mantenimiento == null){
            response.put("mensaje", "Persona con el ID ".concat(idMantenimiento.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Mantenimiento>(mantenimiento, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@Valid @RequestBody  Mantenimiento mantenimiento, BindingResult result){

        Mantenimiento mantenimientoCreado= null;
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
            mantenimientoCreado = mantenimientoService.save(mantenimiento);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al guardar en la Base de Datos");
            response.put("errores", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Registro creado con éxito");
        response.put("persona", mantenimientoCreado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{idMantenimiento}")
    public ResponseEntity<?> editar(@Valid @RequestBody Mantenimiento mantenimiento, BindingResult result, @PathVariable Long idMantenimiento){
        Mantenimiento mantenimientoActual = mantenimientoService.findById(idMantenimiento);
        Mantenimiento mantenimientoActualizado= null;

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()){
            List<String> errores =result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }


        if (mantenimientoActual == null){
            response.put("mensaje", "Error: No se puede editar. Persona con el ID ".concat(idMantenimiento.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            mantenimientoActual.setCodigoMantenimiento(mantenimiento.getCodigoMantenimiento());
            mantenimientoActual.setNombreMantenimiento(mantenimiento.getNombreMantenimiento());
            mantenimientoActual.setCostoMantenimiento(mantenimiento.getCostoMantenimiento());

            mantenimientoActualizado=mantenimientoService.save(mantenimientoActual);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar registro en la Base de Datos");
            response.put("errores", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Registro actualizado con éxito");
        response.put("persona", mantenimientoActualizado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED) ;
    }

    @DeleteMapping("/eliminar/{idMantenimiento}")
    public ResponseEntity<?> eliminar(@PathVariable Long idMantenimiento){
        Map<String, Object> response = new HashMap<>();
        try {
            mantenimientoService.delete(idMantenimiento);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar registro en la Base de Datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK) ;

    }
}
