import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.ReleaseId;
import org.kie.api.io.KieResources;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import java.nio.charset.StandardCharsets;

public class DroolsMain {
    public static void main(String[] args) {
        KieServices kieServices = KieServices.get();

        KieResources resources = kieServices.getResources();
        byte[] bytes = ("rule aa" +
                " when " +
                "then System.out.println(\"hello\"); " +
                "end")
                .getBytes(StandardCharsets.UTF_8);
        Resource resource = resources.newByteArrayResource(bytes, "UTF-8");
        resource.setSourcePath("abc.drl");

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(resource);

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
//        System.out.println(kieBuilder.getResults());

        ReleaseId defaultReleaseId = kieServices.getRepository().getDefaultReleaseId();
        KieContainer kieContainer = kieServices.newKieContainer(defaultReleaseId);


        KieSession kieSession = kieContainer.newKieSession();
        kieSession.fireAllRules();
    }
}
