package gradle.intro;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import java.io.File;
import java.util.List;

public class ImageCrawlerRunner {
    private final CrawlController crawler;
    private final String storageFolder;
    private final int numberCrawlers;
    private final List<String> crawlDomains;

    public ImageCrawlerRunner(List<String> crawlDomains, String storageFolder, int numberCrawlers) {
        this.crawlDomains = crawlDomains;
        this.crawler = createCrawler(crawlDomains);
        this.storageFolder = storageFolder;
        this.numberCrawlers = numberCrawlers;
    }

    public void run() {
        CrawlController.WebCrawlerFactory<ImageCrawler> factory = createCrawlerFactory(crawlDomains);
        this.crawler.start(factory, this.numberCrawlers);
    }

    private CrawlController createCrawler(List<String> crawlDomains) {
        CrawlConfig config = new CrawlConfig();
        createFolderIfNeeded(getTmpFolder());
        config.setCrawlStorageFolder(getTmpFolder());
        config.setIncludeBinaryContentInCrawling(true);
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        try {
            CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
            for (String domain : crawlDomains) {
                controller.addSeed(domain);
            }
            return controller;
        } catch (Exception e) {
            throw new RuntimeException("Error creating crawler.", e);
        }
    }

    private CrawlController.WebCrawlerFactory<ImageCrawler> createCrawlerFactory(List<String> crawlDomains) {
        File storageFolder = new File(getResultsFolder());
        createFolderIfNeeded(getResultsFolder());
        return () -> new ImageCrawler(storageFolder, crawlDomains);
    }

    private String getResultsFolder() {
        return this.storageFolder + "/results";
    }

    private String getTmpFolder() {
        return this.storageFolder + "/tmp";
    }

    private void createFolderIfNeeded(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (!created) {
                throw new RuntimeException("Error creating folder: " + folderPath);
            }
        }
    }
}