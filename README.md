# 🖼️ Image Bit Glitcher in Java

Welcome to the **Image Bit Glitcher**, a fun and educative Java project that lets you flip bits in an image file to create cool **glitch effects** — right from the byte level!

---

## 🚀 What is This?

This is a Java-based tool that:

- Reads an image file (`.png`)
- Randomly flips bits in the byte array
- Saves the result as a new glitched image

You get to explore:

- How files are stored as byte streams
- Bitwise manipulation in Java
- How file corruption/glitching works at a binary level

---

## 📸 Demo

> 🎯 Place any `original.png` in the same folder as the `.java` file and run the program.
> You’ll get a new `glitched.png` image that looks **trippy** or **corrupted** (but often still opens, chances are there it can totally corrupt).

---

## 🧠 Under the Hood: Byte-Level Bit Flipping

### 🧵 Step-by-step Breakdown:

#### 1. **Read Image as Byte Stream**

```java
FileInputStream fis = new FileInputStream("original.png");
byte[] imageBytes = fis.readAllBytes();
fis.close();
```

* **What’s happening:** Java reads the entire file into a `byte[]`, which is essentially a raw stream of binary data.
* At this level, the PNG file is just a sequence of `0s` and `1s`.

#### 2. **Flip Random Bits**

```java
int index = 100 + rand.nextInt(imageBytes.length - 100);
int bitPosition = rand.nextInt(8);
imageBytes[index] ^= (1 << bitPosition);
```

* We're skipping the first 100 bytes to  **avoid corrupting the PNG header** , which contains critical file format info.
* `rand.nextInt(8)` gives a bit index between `0` and `7` (because each byte is 8 bits).
* We use bitwise XOR (`^=`) with a bit mask to **flip** the selected bit:
  * `1 << bitPosition` creates a binary mask like `00001000`.
  * XORing this with the original byte flips that bit.

#### 3. **Write the Result**

```java
FileOutputStream fos = new FileOutputStream("glitched.png");
fos.write(imageBytes);
fos.close();
```

* The modified byte array is now written back to a new file.
* If done carefully (i.e., not breaking the format headers), the file still opens — but with  **visual distortions** !

---

## 🔍 Why This Works

Images like PNG are structured with binary headers, metadata, and pixel data.

If you corrupt parts of the  **pixel data** , the file remains readable — but the pixels may shift colors, patterns, or get scrambled!

However, if you mess with the **signature** (e.g., the first 8 bytes in a PNG), the image viewer will likely reject the file entirely.

This experiment gives you a look into:

* How fragile (yet resilient) digital files are
* What happens when you tweak binary data at a low level
* A glimpse into digital signal manipulation, file formats, and encoding

---

## 🛠️ Customize It

You can change:

* `numFlips` — more flips = more glitching (but be careful!)
* `index range` — target only specific parts of the image
* `bit logic` — instead of XOR, try AND, OR, or even replacing bytes

You can also try it on:

* `.jpg`, `.bmp`, `.gif`, or even `.pdf` (you’ll get weird results 👀)

---

## 📁 Files in This Project

| File                   | Description                        |
| ---------------------- | ---------------------------------- |
| `ImageGlitcher.java` | Main Java program                  |
| `original.png`       | Source image file (you provide it) |
| `glitched.png`       | Output glitched image              |

---

## 📚 Educational Value

This project teaches:

* File I/O in Java (`FileInputStream`, `FileOutputStream`)
* Bitwise operations (`XOR`, bitmasking)
* Digital image formats
* Safe manipulation of binary streams
* The delicate balance between file structure and data