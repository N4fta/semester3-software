package gradle.intro;

import java.util.List;

public class ImageCrawlerMain {
    private static final int NUMBER_OF_CRAWLERS = 2;
    private static final String PROJECT_DIR = System.getProperty("user.dir");
    private static final String CRAWLER_STORAGE_FOLDER_PATH = PROJECT_DIR + "/crawler4j/";

    public static void main(String[] args) {
        List<String> crawlDomains = List.of("https://www.wired.com/");
        ImageCrawlerRunner imageCrawlerRunner = new ImageCrawlerRunner(crawlDomains, CRAWLER_STORAGE_FOLDER_PATH,
                NUMBER_OF_CRAWLERS);
        imageCrawlerRunner.run();
    }
}