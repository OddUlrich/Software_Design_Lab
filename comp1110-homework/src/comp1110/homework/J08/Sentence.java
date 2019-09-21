package comp1110.homework.J08;

public class Sentence {
//    public static void main(String[] args) {
//        Sentence hasOne = new Sentence(1);
//        hasOne.words[0] = new Word("Test", Word.Type.NOUN);
//        System.out.println(hasOne.isValid());
//    }

    public Word[] words;
    public int size;

    Sentence(int size) {
        this.size = size;
        this.words = new Word[size];  // When constructing, initialize the field of array instance variable.
    }

    public boolean isValid() {
        if (this.size < 1) {  // Rule 1: The length is at least 1.
            return false;
        }

        boolean bVerbFlag = false;
        for (int idx = 0; idx < this.size; idx++) {
            if (idx == this.size - 1) {  // Check the last word.
                if ((this.words[idx].type == Word.Type.NOUN && bVerbFlag)
                    || (this.words[idx].type == Word.Type.VERB && !bVerbFlag)) {
                    // Rule 5: The last word is a NOUN or the only VERB (Rule 4).
                    return true;
                } else {
                    return false;
                }
            }

            switch (this.words[idx].type) {
                case NOUN:
                    if (this.words[idx + 1].type == Word.Type.VERB) {  // Rule 2: A NOUN must be followed by a VERB.
                        break;
                    } else {
                        return false;
                    }
                case ADJECTIVE:
                    if (this.words[idx + 1].type == Word.Type.ADJECTIVE
                        || this.words[idx + 1].type == Word.Type.NOUN) {  // Rule 3: An ADJECTIVE must be followed by another ADJECTIVE or a NOUN.
                        break;
                    } else {
                        return false;
                    }
                case VERB:
                    if (bVerbFlag) {  // Rule 4: Only one VERB.
                        return false;
                    } else {
                        bVerbFlag = true;
                        break;
                    }
                default:
                    return false;
            }
        }
        return true;
    }

    public static class Word {
        String value;
        Type type;

        public enum Type {
            NOUN,
            ADJECTIVE,
            VERB
        }

        Word(String value, Type type) {
            this.value = value;
            this.type = type;
        }
    }
}
