package com.aitijhya.vortex.exceptions;

public class InvalidFileType extends RuntimeException {
    public InvalidFileType() {
        super("Only mp4 files are supported");
    }
}
