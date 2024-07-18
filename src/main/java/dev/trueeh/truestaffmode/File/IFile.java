package dev.trueeh.truestaffmode.File;

public interface IFile {
    String getFileName();

    boolean getBoolean(final String path);

    int getInt(final String path);

    double getDouble(final String path);

    long getLong(final String path);

    void set(final String path, final Object newValue);

}
