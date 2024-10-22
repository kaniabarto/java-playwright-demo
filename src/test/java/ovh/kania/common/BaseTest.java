package ovh.kania.common;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import ovh.kania.utils.StringUtils;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {

    static Playwright pw;
    static Browser browser;
    private BrowserContext browserContext;
    protected Page page;

    @BeforeAll
    static void beforeAll(){
        pw = Playwright.create();
        browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
    }

    @BeforeEach
    void beforeEachBaseTest(){
        browserContext = browser.newContext();
        browserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        page = browserContext.newPage();
    }

    @AfterEach
    void afterEachBaseTest(TestInfo testInfo){
        String traceName ="traces/trace_"
                + StringUtils.removeRoundBrackets(testInfo.getDisplayName())
                + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"))
                + ".zip";
        browserContext.tracing().stop(new Tracing.StopOptions().setPath(Paths.get(traceName)));
        browserContext.close();
    }

    @AfterAll
    static void afterAll(){
        browser.close();
        pw.close();
    }
}
