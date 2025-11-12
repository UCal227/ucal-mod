package ucal.azyr;

import net.fabricmc.api.ModInitializer;
import ucal.azyr.registry.AzyrItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Azyr implements ModInitializer {
	public static final String MOD_ID = "azyr";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Loading Azyr");
		AzyrItems.initialize();
	}
}