package com.aitijhya.vortex.events;

import com.aitijhya.vortex.entities.MediaAsset;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class VideoUploadedEvent  {
    private final MediaAsset mediaAsset;
}
