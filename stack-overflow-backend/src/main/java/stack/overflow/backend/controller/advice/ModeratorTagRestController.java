package stack.overflow.backend.controller.advice;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stack.overflow.backend.service.TagService;


@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1/moderator/tags")
public class ModeratorTagRestController {
    private final TagService tagService;

    @DeleteMapping("/{tagId}")
    public ResponseEntity<Void> delete(@PathVariable("tagId") Long tagId) {
        tagService.delete(tagId);
        return ResponseEntity.ok().build();
    }

}