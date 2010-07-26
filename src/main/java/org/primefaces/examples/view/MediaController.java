package org.primefaces.examples.view;

import java.io.InputStream;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class MediaController {
	
	private StreamedContent media;
	
	public MediaController() {
		InputStream stream = this.getClass().getResourceAsStream("ria_with_primefaces.mov");
		media = new DefaultStreamedContent(stream, "video/quicktime");
	}

	public StreamedContent getMedia() {
		return media;
	}

	public void setMedia(StreamedContent media) {
		this.media = media;
	}

}
