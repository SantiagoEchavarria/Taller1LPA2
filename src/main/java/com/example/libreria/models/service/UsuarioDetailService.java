package com.example.libreria.models.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.libreria.models.dao.UsuarioRDAO;
import com.example.libreria.models.entity.Rol;
import com.example.libreria.models.entity.UsuarioR;

@Service
public class UsuarioDetailService implements UserDetailsService{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UsuarioRDAO usuarioRDAO;

    public UsuarioDetailService(UsuarioRDAO usuarioRDAO) {
		this.usuarioRDAO = usuarioRDAO;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsuarioR usuarioR = usuarioRDAO.findByNombre(username);

        if(usuarioR == null){
            logger.info("*** Error de autenticación, el usuario ...");
            throw new UsernameNotFoundException("Error de autenticación, el usuario ...");
        }

        //Si existe el usuario obtener los roles
        List<GrantedAuthority> roles = new ArrayList<>();
        for (Rol rol : usuarioR.getRoles()){
            logger.info("Rol: "+ rol.getNombre());
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }

        //Si no hay roles
        if(roles.isEmpty()){
            logger.warn("El usuario: "+ usuarioR.getNombre()+ " no tiene roles asignados");
            throw new UsernameNotFoundException("El usuario: "+usuarioR.getNombre()+ " no tiene roles asignados.");
        }

        //Retonar un usuario de tipo security
        return new User(usuarioR.getNombre(), usuarioR.getClave(), usuarioR.isActivo(), true, true, true, roles);
    }  
}
