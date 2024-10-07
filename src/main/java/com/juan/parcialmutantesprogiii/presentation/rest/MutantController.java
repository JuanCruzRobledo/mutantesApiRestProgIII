import com.juan.parcialmutantesprogiii.business.facade.IMutantFacade;
import com.juan.parcialmutantesprogiii.domain.dtos.DnaRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    private final IMutantFacade mutantFacade;

    public MutantController(IMutantFacade mutantFacade) {
        this.mutantFacade = mutantFacade;
    }

    @PostMapping
    public ResponseEntity<?> isMutant(@RequestBody DnaRequest dnaRequest) {
        boolean isMutant = mutantFacade.isMutant(dnaRequest.getDna());
        if (isMutant) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
