package com.skewwhiffy.auraltester.controller;

import com.skewwhiffy.auraltester.dto.KeySignatureResponse;
import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory;
import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.service.AbcService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class KeySignatureController {
    private final InternalNotationFactory internalNotationFactory;
    private final AbcService abcService;
  @RequestMapping("/api/keySignature")
  public KeySignatureResponse get(
          @RequestParam String clef,
          @RequestParam Optional<String> keySignature
  ) {
    val clefObject = internalNotationFactory.clef(clef);
    val note = internalNotationFactory
      .getNote(keySignature.orElse("C"))
      .note();
    val key = Key.major(note);
    val abc = abcService.getAbc(clefObject, key).getAbc();
    return new KeySignatureResponse(abc);
  }
}
