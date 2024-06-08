package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.controller.comand.UserComand;
import com.mobabuild.api_build.controller.comand.UserLoginComand;
import com.mobabuild.api_build.controller.dto.*;
import com.mobabuild.api_build.entities.*;
import com.mobabuild.api_build.entities.Object;
import com.mobabuild.api_build.persistence.IUserDAO;
import com.mobabuild.api_build.service.IUserService;
import com.mobabuild.api_build.utils.BlobUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDAO userDAO;

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userDAO.findAll();

        List<UserDTO> userDTOs = users.stream().map(user -> {
            // Inicializar la colección authorities antes de acceder a ella
            Hibernate.initialize(user.getAuthorities());

            List<AuthorityDTO> authorityDTOs = user.getAuthorities().stream()
                    .map(authority -> AuthorityDTO.builder()
                            .id(authority.getId())
                            .name(authority.getName())
                            .build())
                    .collect(Collectors.toList());

            return UserDTO.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .user_name(user.getUser_name())
                    .pass(user.getPass())
                    .image(BlobUtils.blobToBytes(user.getImage()))
                    .authorities(authorityDTOs)
                    .build();
        }).collect(Collectors.toList());

        return userDTOs;
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<User> userOptional = userDAO.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();

            if (user.getBuilds() == null) {
                user.setBuilds(new ArrayList<>());
            }

            // Inicializar la colección authorities antes de acceder a ella
            Hibernate.initialize(user.getAuthorities());

            // Convertir la lista de Authority a AuthorityDTO
            List<AuthorityDTO> authorityDTOs = user.getAuthorities().stream()
                    .map(authority -> AuthorityDTO.builder()
                            .id(authority.getId())
                            .name(authority.getName())
                            .build())
                    .collect(Collectors.toList());

            List<BuildDTO> buildDTOList = user.getBuilds().stream()
                    .map(this::createBuildDTO)
                    .collect(Collectors.toList());

            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .user_name(user.getUser_name())
                    .pass(user.getPass())
                    .image(BlobUtils.blobToBytes(user.getImage()))
                    .authorities(authorityDTOs)
                    .builds(buildDTOList)
                    .build();
            return  userDTO;
        }
        return UserDTO.builder().build();
    }

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userDAO.deleteById(id);
    }

    @Override
    public UserDTO findByUserAndPass(UserLoginComand userLoginComand) {
        User user = userDAO.findByUserAndPass(userLoginComand.getEmail(), userLoginComand.getPass());

        List<AuthorityDTO> authorityDTOs = user.getAuthorities().stream()
                .map(authority -> AuthorityDTO.builder()
                        .id(authority.getId())
                        .name(authority.getName())
                        .build())
                .collect(Collectors.toList());

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .user_name(user.getUser_name())
                .pass(user.getPass())
                .image(BlobUtils.blobToBytes(user.getImage()))
                .authorities(authorityDTOs)
                .build();

        return  userDTO;
    }

    @Override
    public UserDTO updateUser(UserComand userComand) {
        Optional<User> existingUserOptional = userDAO.findById(userComand.getId());
        List<Authority> authorities = new ArrayList<>();
        if (existingUserOptional.isPresent()) {
            User userOptionalExist = existingUserOptional.get();
            userOptionalExist.setEmail(userComand.getEmail());
            userOptionalExist.setUser_name(userComand.getUser_name());
            userOptionalExist.setPass(userComand.getPass());
            userOptionalExist.setImage(BlobUtils.bytesToBlob(userComand.getImage()));
            userOptionalExist.setAuthorities(userComand.getAuthorities());

            List<AuthorityDTO> authorityDTOs = userOptionalExist.getAuthorities().stream()
                    .map(authority -> AuthorityDTO.builder()
                            .id(authority.getId())
                            .name(authority.getName())
                            .build())
                    .collect(Collectors.toList());

            UserDTO userDTO = UserDTO.builder()
                    .id(userOptionalExist.getId())
                    .email(userOptionalExist.getEmail())
                    .user_name(userOptionalExist.getUser_name())
                    .pass(userOptionalExist.getPass())
                    .image(BlobUtils.blobToBytes(userOptionalExist.getImage()))
                    .authorities(authorityDTOs)
                    .build();

            userDAO.save(userOptionalExist);
            return userDTO;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public User addUserWithoutImage(String email, String user_name, String pass) {
        User newUser = User.builder()
                .email(email)
                .user_name(user_name)
                .pass(pass)
                .build();
        return userDAO.save(newUser);
    }

    private BuildDTO createBuildDTO(Build build){

        UserDTO userDTO = createUserDTO(build.getUser());

        ChampionsDTO championsDTO = ChampionsDTO.builder()
                .id(build.getChampions().getId())
                .name(build.getChampions().getName())
                .image(BlobUtils.blobToBytes(build.getChampions().getImage()))
                .build();

        List<SpellSetDTO> spellSetsDTO = build.getSpellSets().stream()
                .map(spellSet -> SpellSetDTO.builder()
                        .id(spellSet.getId())
                        .name(spellSet.getName())
                        //.build(createBuildDTO(spellSet.getBuild()))
                        .spells(spellSet.getSpells().stream()
                                .map(this::createSpellDTO)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        List<ObjectSetDTO> objectSetsDTO = build.getObjectSet().stream()
                .map(objectSet -> ObjectSetDTO.builder()
                        .id(objectSet.getId())
                        .name(objectSet.getName())
                        //.build(createBuildDTO(objectSet.getBuild()))
                        .objects(objectSet.getObjects().stream()
                                .map(this::createObjectDTO)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        List<RuneSetDTO> runeSetsDTO = build.getRuneSet().stream()
                .map(runeSet -> RuneSetDTO.builder()
                        .id(runeSet.getId())
                        .name(runeSet.getName())
                        .main_rune(runeSet.getMain_rune())
                        .main_sub_rune(runeSet.getMain_sub_rune())
                        .secondary_rune(runeSet.getSecondary_rune())
                        .secondary_sub_rune(runeSet.getSecondary_sub_rune())
                        .additional_advantages(runeSet.getAdditional_advantages())
                        //.build(createBuildDTO(runeSet.getBuild()))
                        .build())
                .collect(Collectors.toList());

        return BuildDTO.builder()
                .id(build.getId())
                .buildName(build.getBuildName())
                .user(userDTO)
                .champions(championsDTO)
                .spellSets(spellSetsDTO)
                .objectSet(objectSetsDTO)
                .runeSet(runeSetsDTO)
                .build();
    }

    private UserDTO createUserDTO(User user){

        return  UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .user_name(user.getUser_name())
                .pass(user.getPass())
                .image(BlobUtils.blobToBytes(user.getImage()))
                .build();
    }

    private FavoriteBuildDTO createFavoriteBuildDTO(FavoriteBuild favoriteBuild){

        UserDTO userDTO = createUserDTO(favoriteBuild.getUser());


        return FavoriteBuildDTO.builder()
                .id(favoriteBuild.getId())
                .user(userDTO)
                .builds(favoriteBuild.getBuilds())
                .build();
    }

    private SpellDTO createSpellDTO(Spell spell) {
        return SpellDTO.builder()
                .id(spell.getId())
                .name(spell.getName())
                .champion_level(spell.getChampion_level())
                .game_mode(spell.getGame_mode())
                .description(spell.getDescription())
                .cooldown(spell.getCooldown())
                .image(BlobUtils.blobToBytes(spell.getImage()))
                .build();
    }

    private ObjectDTO createObjectDTO(Object object) {
        return ObjectDTO.builder()
                .id(object.getId())
                .name(object.getName())
                .image(BlobUtils.blobToBytes(object.getImage()))
                .build();
    }
}
