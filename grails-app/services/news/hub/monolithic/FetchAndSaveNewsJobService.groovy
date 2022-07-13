package news.hub.monolithic

import groovy.transform.CompileStatic
import org.springframework.scheduling.annotation.Scheduled
import utils.DateUtils


@CompileStatic
class FetchAndSaveNewsJobService {
    boolean lazyInit = false

    NewsService newsService

    @Scheduled(fixedRate = 3000000L)
    void execute() {
        println( "${DateUtils.getNowString()} - Start JOB - FetchAndSaveNews")
        newsService.fetchAndSave()
        println( "${DateUtils.getNowString()} - Finish JOB - FetchAndSaveNews")
    }
}
