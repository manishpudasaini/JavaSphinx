package VoiceCommand;


import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.Result;

import java.io.IOException;

public class VoiceAssistance {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();

        //set language to english
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");

        //set dictionary path generated from Sphinx knowledge base generator
        configuration.setDictionaryPath("Files/2878.dic");

        //set languageModel path generated from Sphinx knowledge base generator
        configuration.setLanguageModelPath("Files/2878.lm");

       try{
           LiveSpeechRecognizer speech = new LiveSpeechRecognizer(configuration);
           speech.startRecognition(true);


           SpeechResult result;

           while ((result = speech.getResult()) != null){

               //command we give voice command
               String voiceCommand = result.getHypothesis();
               System.out.println("Our voice command is :: "+ voiceCommand);

               //condition after we give command
               if(voiceCommand.equalsIgnoreCase("Open chrome")){

                   //here cmd.exe /c chrome will  open google chrome or if already open ten open new tab and navigate to youtube
                   Runtime.getRuntime().exec("cmd.exe /c start chrome www.youtube.com");

               } else if (voiceCommand.equalsIgnoreCase("Close chrome")) {
                   Runtime.getRuntime().exec("cmd.exe /c TASK-KILL /IM chrome.exe");
               }

           }
       }catch (IOException e){
           e.printStackTrace();
       }
    }
}
