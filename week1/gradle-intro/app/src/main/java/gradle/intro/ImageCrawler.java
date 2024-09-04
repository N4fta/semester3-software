package gradle.intro;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.BinaryParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class ImageCrawler extends WebCrawler {
    private static final Pattern exclusionPatterns = Pattern.compile(
            ".*(\\.(css|js|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf" + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
    private static final Pattern imgPatterns = Pattern.compile(".*(\\.(bmp|gif|jpe?g|png|tiff?))$");
    private final File storageFolder;
    private final List<String> crawlDomains;

    public ImageCrawler(File storageFolder, List<String> crawlDomains) {
        this.storageFolder = storageFolder;
        this.crawlDomains = Collections.unmodifiableList(crawlDomains);
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        if (exclusionPatterns.matcher(href).matches()) {
            return false;
        }
        if (imgPatterns.matcher(href).matches()) {
            return true;
        }
        for (String domain : crawlDomains) {
            if (href.startsWith(domain)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        if (!imgPatterns.matcher(url).matches() || !(page.getParseData() instanceof BinaryParseData)) {
            return;
        }
        String filename = getUniqueFileName(url);
        try {
            Files.write(Path.of(filename), page.getContentData());
            WebCrawler.logger.info("Stored: {}", url);
        } catch (IOException e) {
            WebCrawler.logger.error("Failed to write file: {}", filename, e);
        }
    }

    private String getUniqueFileName(String url) {
        String extension = url.substring(url.lastIndexOf('.'));
        String hashedName = UUID.randomUUID() + extension;
        return storageFolder.getAbsolutePath() + '/' + hashedName;
    }
}