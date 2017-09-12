package io.sbelkin.codenames.controller;

import io.sbelkin.codenames.engine.GenerateGameBoard;
import io.sbelkin.codenames.exception.InvalidBoardGameInput;
import io.sbelkin.codenames.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/codename")
public class CodenamesController {

  @Autowired
  private GenerateGameBoard generateGameBoard;

  @GetMapping("/")
  @ResponseBody
  public Game getSimpleRound() throws InvalidBoardGameInput {
    return generateGameBoard.generateDefault();
  }
}