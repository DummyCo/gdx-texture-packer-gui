
package com.crashinvaders.texturepackergui.services.model;

import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.github.czyzby.autumn.processor.event.EventDispatcher;
import com.github.czyzby.kiwi.util.common.Strings;
import com.crashinvaders.texturepackergui.events.PackPropertyChangedEvent;
import com.crashinvaders.texturepackergui.events.PackPropertyChangedEvent.Property;

import java.io.File;

public class PackModel {

    private Settings settings;
    private String name = "";
	private String filename = "";
	private String inputDir = "";
	private String outputDir = "";

    private EventDispatcher eventDispatcher;

	public PackModel () {

        settings = new Settings();
		settings.maxWidth = 2048; // Default settings.maxWidth value (1024) is outdated and 2048 is recommended
		settings.maxHeight = 2048; // Default settings.maxHeight value (1024) is outdated and 2048 is recommended
	}

	public PackModel (PackModel pack) {
		settings = new Settings(pack.settings);
		this.name = pack.name;
		this.filename = pack.filename;
		this.inputDir = pack.inputDir;
		this.outputDir = pack.outputDir;
	}

    public void setEventDispatcher(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    public void setName (String name) {
        if (Strings.equals(this.name, name)) return;

        this.name = name;
        if (eventDispatcher != null) {
            eventDispatcher.postEvent(new PackPropertyChangedEvent(this, Property.NAME));
        }
	}

	public void setFilename (String filename) {
        if (Strings.equals(this.filename, filename)) return;

		this.filename = filename;
        if (eventDispatcher != null) {
            eventDispatcher.postEvent(new PackPropertyChangedEvent(this, Property.FILENAME));
        }
	}

	public void setInputDir(String inputDir) {
        if (Strings.equals(this.inputDir, inputDir)) return;

        this.inputDir = inputDir;
        if (eventDispatcher != null) {
            eventDispatcher.postEvent(new PackPropertyChangedEvent(this, Property.INPUT));
        }
	}

	public void setOutputDir(String outputDir) {
        if (Strings.equals(this.outputDir, outputDir)) return;

        this.outputDir = outputDir;
        if (eventDispatcher != null) {
            eventDispatcher.postEvent(new PackPropertyChangedEvent(this, Property.OUTPUT));
        }
	}

	public String getName () {
		return name;
	}

	public String getFilename () {
		return filename;
	}

	public String getInputDir() {
		return inputDir;
	}

	public String getOutputDir() {
		return outputDir;
	}

	public Settings getSettings () {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getCanonicalName() {
		return name.trim().isEmpty() ? "unnamed" : name;
	}

	public String getCanonicalFilename() {
		String filename = this.filename.trim().isEmpty() ? getCanonicalName() : this.filename;
		return filename;
	}

	/**
	 * @return may be null
	 */
	public String getAtlasPath() {
		String atlasPath = null;
		if (outputDir != null && !outputDir.trim().isEmpty()) {
			String filename = getCanonicalFilename();
			if (!filename.contains(".")) {
				filename += ".atlas";
			}
			atlasPath = outputDir + File.separator + filename;
		}
		return atlasPath;
	}
}
