package servicii;

import models.Cititor;
import repository.BibliotecaRepo;

public class CititorService {
    private static BibliotecaRepo repoInstance = BibliotecaRepo.getInstance();
    private LoggingService logger = LoggingService.getInstance();

    public void AdaugaCititor(Cititor cititor){
        logger.log("adaugaCititor");
        repoInstance.addCititor(cititor);
    }

    public void StergeCititor(String nume){
        logger.log("StergeCititor");
        repoInstance.remCititor(nume);
    }
}
