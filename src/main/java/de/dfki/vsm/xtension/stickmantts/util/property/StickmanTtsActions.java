package de.dfki.vsm.xtension.stickmantts.util.property;

import de.dfki.common.interfaces.StageRoom;
import de.dfki.vsm.extensionAPI.ExportableCompletion;
import de.dfki.vsm.util.stickman.StickmanRepository;

import java.util.List;

public class StickmanTtsActions implements ExportableCompletion{
    private final StickmanRepository stickmanFactory;

    public StickmanTtsActions(StickmanRepository stickmanFactory) {
        this.stickmanFactory = stickmanFactory;
    }

    @Override
    public List<String> getExportableActions() {
        StageRoom stickmanStageC = stickmanFactory.createStickman();
        return stickmanStageC.getAnimations();
    }
}
