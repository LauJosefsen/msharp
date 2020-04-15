package msharp.MinecraftClasses;

import java.util.HashMap;
import java.util.Map;

public class Block {
    private String _blockName;
    private Map<String, String> _metadata = new HashMap<>();


    public Block(String blockName) {
        _blockName = blockName;
    }

    public Block(String blockName, Map<String, String> metadata) {
        this(blockName);
        _metadata = metadata;
    }

    @Override
    public String toString() {
        StringBuilder formattedMetadata = new StringBuilder();
        for (Map.Entry<String, String> meta_entry : _metadata.entrySet()) {
            formattedMetadata.append(meta_entry.getKey()).append("=").append(meta_entry.getValue()).append(",");
        }
        if (formattedMetadata.length() > 0) {
            formattedMetadata = new StringBuilder(formattedMetadata.substring(0, formattedMetadata.length() - 1)); // remove last ','
            return _blockName + '[' + formattedMetadata + ']';
        } else return _blockName;
    }

    public String get_blockName() {
        return _blockName;
    }

    public void set_blockName(String _blockName) {
        this._blockName = _blockName;
    }

    public Map<String, String> get_metadata() {
        return _metadata;
    }

    public void set_metadata(Map<String, String> _metadata) {
        this._metadata = _metadata;
    }
}
