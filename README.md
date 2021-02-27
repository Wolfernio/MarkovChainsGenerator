# ChatterBot
Generating text using frequency analysis.

## What is it?
This program generates text based on the text that you give it. If you were to give it a transcript of a speech by Donald Trump, it would spit something back at you that *sounded like* Mr Trump, but would read like nonsense. In fact, anything produced by this program is nonsense.

## How do I use it?
Paste whatever text you want to "mimic" into the top text box, hit the button and enjoy the output. Note that the results tend to be better, the more sample text is given. A sample text file is available [here](src/michaelryan/ChatterBot/sampletext) (it's a Trump speech).

## How does it work?
With frequency analysis. For each word in the input, I log what word comes after it. This way, I can build a mapping with words mapping to lists of words.

For example, for the input: `the dog the dog the cat` would produce the following mapping:
```
the -> [dog, cat]
dog -> [the]
cat -> []
```

I also count the frequencies of each word appearing after a given word, so the mapping actually looks like this:

```
the -> [dog -> 2, cat -> 1]
dog -> [the -> 2]
cat -> []
```
A simple roulette selection on `the` gives me a two thirds chance that the next word produced is `dog`, and a one third chance it'll be `cat`. A totally random word is chosen to start, then this selection is repeated over and over again.

Commas and full stops are treated as words too.

## What if I don't trust you?
After looking through the code, feel free to compile with `mvn package`. This will create an executable jar in the `target` directory. Otherwise, check the [releases](https://github.com/michael-ryan/ChatterBot/releases) page and download the latest jar.

## Why would I use it?
I don't know. It's a bit of a laugh I guess.
