package com.sisgpn.backend.api.controller;

import com.sisgpn.backend.api.model.Circuito;
import com.sisgpn.backend.api.model.Distrito;
import com.sisgpn.backend.api.model.Mantenimiento;
import com.sisgpn.backend.api.service.ICircuitoService;
import com.sisgpn.backend.api.service.IDistritoService;
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
@RequestMapping("/circuitos")
public class CircuitoController {

    @Autowired
    private ICircuitoService circuitoService;

    @GetMapping("/vertodo/page/{page}")
    public Page<Circuito> vertodo(@PathVariable Integer page){
        return circuitoService.findAll(PageRequest.of(page,5));
    }

    @GetMapping("/ver/{idCircuito}")
    public ResponseEntity<?> buscarId(@PathVariable Long idCircuito){
        Circuito circuito=null;

        Map<String, Object> response = new HashMap<>();

        try {
            circuito= circuitoService.findById(idCircuito);
        }catch(DataAccessException e){
            response.put("mensaje", "Error al consultar la Base de Datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (circuito == null){
            response.put("mensaje", "Distrito con el ID ".concat(idCircuito.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Circuito>(circuito, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@Valid @RequestBody  Circuito circuito, BindingResult result){

        Circuito circuitoCreado= null;

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
            circuitoCreado = circuitoService.save(circuito);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al guardar en la Base de Datos");
            response.put("errores", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Registro creado con éxito");
        response.put("distrito", circuitoCreado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{idCircuito}")
    public ResponseEntity<?> editar(@Valid @RequestBody Circuito circuito,BindingResult result, @PathVariable Long idCircuito){
        Circuito circuitoActual = circuitoService.findById(idCircuito);
        Circuito circuitoActualizado= null;

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()){
            List<String> errores =result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }


        if (circuitoActual == null){
            response.put("mensaje", "Error: No se puede editar. Persona con el ID ".concat(idCircuito.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            circuitoActual.setCodigoCircuito(circuito.getCodigoCircuito());
            circuitoActual.setNombreCircuito(circuito.getNombreCircuito());


            circuitoActualizado=circuitoService.save(circuitoActual);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar registro en la Base de Datos");
            response.put("errores", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Registro actualizado con éxito");
        response.put("distrito", circuitoActualizado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED) ;
    }

    @DeleteMapping("/eliminar/{idCircuito}")
    public ResponseEntity<?> eliminar(@PathVariable Long idCircuito){
        Map<String, Object> response = new HashMap<>();
        try {
            circuitoService.delete(idCircuito);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar registro en la Base de Datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK) ;
    }

    @GetMapping("/distritos")
    public List<Distrito> listarDistritos(){
        return circuitoService.findAllDistritos();
    }

}
