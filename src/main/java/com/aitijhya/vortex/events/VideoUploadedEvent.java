package com.aitijhya.vortex.events;

import com.aitijhya.vortex.entities.MediaAsset;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VideoUploadedEvent  {
    private final MediaAsset mediaAsset;
}
