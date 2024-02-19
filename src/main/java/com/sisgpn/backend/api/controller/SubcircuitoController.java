package com.sisgpn.backend.api.controller;

import com.sisgpn.backend.api.model.Circuito;
import com.sisgpn.backend.api.model.Subcircuito;
import com.sisgpn.backend.api.service.ICircuitoService;
import com.sisgpn.backend.api.service.ISubcircuitoService;
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
@RequestMapping("/subircuitos")
public class SubcircuitoController {

    @Autowired
    private ISubcircuitoService subcircuitoService;

    @GetMapping("/vertodo/page/{page}")
    public Page<Subcircuito> vertodo(@PathVariable Integer page){
        return subcircuitoService.findAll(PageRequest.of(page,5));
    }

    @GetMapping("/ver/{idSubcircuito}")
    public ResponseEntity<?> buscarId(@PathVariable Long idSubcircuito){
        Subcircuito subcircuito=null;

        Map<String, Object> response = new HashMap<>();

        try {
            subcircuito= subcircuitoService.findById(idSubcircuito);
        }catch(DataAccessException e){
            response.put("mensaje", "Error al consultar la Base de Datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (subcircuito == null){
            response.put("mensaje", "Distrito con el ID ".concat(idSubcircuito.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Subcircuito>(subcircuito, HttpStatus.OK);
    }


    @PostMapping("/crear")
    public ResponseEntity<?> crear(@Valid @RequestBody  Subcircuito subcircuito, BindingResult result){

        Subcircuito subcircuitoCreado= null;

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
            subcircuitoCreado = subcircuitoService.save(subcircuito);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al guardar en la Base de Datos");
            response.put("errores", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Registro creado con éxito");
        response.put("distrito", subcircuitoCreado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{idSubcircuito}")
    public ResponseEntity<?> editar(@Valid @RequestBody Circuito circuito,BindingResult result, @PathVariable Long idSubcircuito){
        Subcircuito subcircuitoActual = subcircuitoService.findById(idSubcircuito);
        Subcircuito subcircuitoActualizado= null;

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()){
            List<String> errores =result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }


        if (subcircuitoActual == null){
            response.put("mensaje", "Error: No se puede editar. Persona con el ID ".concat(idSubcircuito.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            subcircuitoActual.setCodigoSubcircuito(subcircuitoActual.getCodigoSubcircuito());
            subcircuitoActual.setNombreSubcircuito(subcircuitoActual.getNombreSubcircuito());
            subcircuitoActualizado=subcircuitoService.save(subcircuitoActual);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar registro en la Base de Datos");
            response.put("errores", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Registro actualizado con éxito");
        response.put("distrito", subcircuitoActualizado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED) ;
    }

    @DeleteMapping("/eliminar/{idSubircuito}")
    public ResponseEntity<?> eliminar(@PathVariable Long idSubircuito){
        Map<String, Object> response = new HashMap<>();
        try {
            subcircuitoService.delete(idSubircuito);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar registro en la Base de Datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK) ;
    }

}
