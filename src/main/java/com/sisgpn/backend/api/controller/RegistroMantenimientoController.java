package com.sisgpn.backend.api.controller;

import com.sisgpn.backend.api.model.Mantenimiento;
import com.sisgpn.backend.api.model.Persona;
import com.sisgpn.backend.api.model.RegistroMantenimiento;
import com.sisgpn.backend.api.model.Vehiculo;
import com.sisgpn.backend.api.service.IRegistroMantenimientoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
@RequestMapping("/registros")
public class RegistroMantenimientoController {

    @Autowired
    IRegistroMantenimientoService registroMantenimientoService;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@Valid @RequestBody RegistroMantenimiento registro, BindingResult result){

        RegistroMantenimiento registroCreado= null;
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
            registroCreado = registroMantenimientoService.save(registro);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al guardar en la Base de Datos");
            response.put("errores", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Registro creado con éxito");
        response.put("registro de mantenimiento", registroCreado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/mantenimientos")
    public List<Mantenimiento> listarMantenimientos(){
        return registroMantenimientoService.findAllMantenimientos();
    }
}
