package ucal.ucallib;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UcalLib implements ModInitializer {
    public static final String MOD_ID = "ucallib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("UcalLib initialized — ready to register custom materials.");
    }
}
