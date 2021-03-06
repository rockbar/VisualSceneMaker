package de.dfki.vsm.xtension.decad.utils;


import de.dfki.vsm.xtension.decad.commands.IsSpeakingCommand;

import java.io.IOException;

class DECADLongPoller {
    private static final String IS_SPEAKING = "1";
    private static final int PAUSE_BEFORE_SPEAKING = 100;
    private static final int PAUSE_AFTER_POLLING = 20;
    private boolean isCharacterSpeaking;

    void pollIsSpeaking(CommandResponseHandler handler) throws IOException, InterruptedException {
        IsSpeakingCommand isSpeakingCommand = new IsSpeakingCommand();
        poll(handler, isSpeakingCommand);
    }

    private void poll(CommandResponseHandler handler, IsSpeakingCommand isSpeaking) throws IOException, InterruptedException {
        waitUntilStartsSpeaking(isSpeaking);
        waitUntilStopsSpeaking(isSpeaking);
        Thread.sleep(PAUSE_BEFORE_SPEAKING);
        handler.handle();

    }

    private void waitUntilStopsSpeaking(IsSpeakingCommand isSpeakingCommand) throws IOException, InterruptedException {
        while (isCharacterSpeaking) {
            pollIsSpeaking(isSpeakingCommand);
            Thread.sleep(PAUSE_AFTER_POLLING);
        }
    }

    private void pollIsSpeaking(IsSpeakingCommand isSpeaking) throws IOException, InterruptedException {
        isSpeaking.execute();
        String response = isSpeaking.getResponse();
        isCharacterSpeaking = response.equals(IS_SPEAKING);
    }

    private void waitUntilStartsSpeaking(IsSpeakingCommand isSpeakingCommand) throws IOException, InterruptedException {
        int counter = 0;
        while (!isCharacterSpeaking && counter <= 4) {
            pollIsSpeaking(isSpeakingCommand);
            Thread.sleep(PAUSE_AFTER_POLLING);
            counter++;
        }
    }
}
