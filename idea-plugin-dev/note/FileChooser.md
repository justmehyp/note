

```text
    FileChooserDescriptor descriptor = createFileChooserDescriptor();
    PathChooserDialog pathChooser = FileChooserFactory.getInstance().createPathChooser(descriptor, project, null);
    pathChooser.choose(null, (files) -> {
        for (VirtualFile file : files) {
            System.out.println(file.getPath());
        }
    });
```


```text
    private FileChooserDescriptor createFileChooserDescriptor() {
        FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleLocalFileDescriptor();
        descriptor.setHideIgnored(false);
        descriptor.setTitle("Select Directory");
        return descriptor;
    }
```

