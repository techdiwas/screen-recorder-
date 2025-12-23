# Contributing to Screen Recorder

Thank you for your interest in contributing to Screen Recorder! This document provides guidelines and instructions for contributing.

## Code of Conduct

- Be respectful and inclusive
- Focus on constructive feedback
- Help others learn and grow
- Maintain a welcoming environment

## How to Contribute

### Reporting Bugs

Before creating a bug report:
1. Check existing issues to avoid duplicates
2. Verify the bug on the latest version
3. Gather relevant information

When creating a bug report, include:
- **Device**: Model and Android version
- **App version**: Version number or commit hash
- **Steps to reproduce**: Detailed, numbered steps
- **Expected behavior**: What should happen
- **Actual behavior**: What actually happens
- **Logs**: Logcat output if available
- **Screenshots/Videos**: If UI-related

**Example:**
```
**Device**: Samsung Galaxy S21, Android 13
**App Version**: 1.0.0 (commit abc123)

**Steps to Reproduce:**
1. Open app
2. Select Internal Audio
3. Tap Start Recording
4. Accept permission
5. Stop recording after 10 seconds

**Expected**: Video saved with internal audio
**Actual**: Video has no audio

**Logs**:
E/ScreenRecorderService: AudioSource.DEFAULT not supported
```

### Suggesting Features

Before suggesting a feature:
1. Check if it already exists
2. Check existing feature requests
3. Consider if it fits the project scope

When suggesting a feature, include:
- **Problem**: What problem does it solve?
- **Solution**: How would it work?
- **Alternatives**: Other ways to solve it
- **Use cases**: When would it be used?

**Example:**
```
**Feature**: Pause/Resume Recording

**Problem**: Users want to pause recording temporarily without creating separate files.

**Solution**: Add pause/resume buttons to notification and UI. MediaRecorder would stop temporarily and resume recording to the same file.

**Use Cases**:
- Pausing during phone calls
- Skipping sensitive information
- Taking breaks in long recordings
```

### Code Contributions

#### Getting Started

1. **Fork the repository**
   ```bash
   git clone https://github.com/your-username/screen-recorder-.git
   cd screen-recorder-
   ```

2. **Create a branch**
   ```bash
   git checkout -b feature/your-feature-name
   # or
   git checkout -b fix/bug-description
   ```

3. **Set up development environment**
   - Install Android Studio
   - Open project
   - Sync Gradle
   - Run on device

#### Coding Standards

**Kotlin Style:**
- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable names
- Keep functions small and focused
- Add comments for complex logic only
- Use nullable types appropriately

**Example:**
```kotlin
// Good
fun createVideoFile(): File {
    val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val fileName = "ScreenRecord_$timestamp.mp4"
    return File(recordingsDir, fileName)
}

// Avoid
fun cf(): File {
    val ts = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    return File(dir, "ScreenRecord_$ts.mp4")  // unclear variable names
}
```

**XML Style:**
- Use meaningful IDs
- Follow Material Design guidelines
- Keep layouts simple
- Use ConstraintLayout for complex layouts

**Example:**
```xml
<!-- Good -->
<Button
    android:id="@+id/recordButton"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="@string/start_recording" />

<!-- Avoid -->
<Button
    android:id="@+id/button1"
    android:text="Start Recording" />  <!-- hardcoded string -->
```

#### Testing

Before submitting:
1. **Build successfully**
   ```bash
   ./gradlew build
   ```

2. **Test on device**
   - Test all affected features
   - Test edge cases
   - Verify no regressions

3. **Check for common issues**
   - No unused imports
   - No hardcoded strings (use strings.xml)
   - No TODO comments without issues
   - Proper resource cleanup

#### Commit Messages

Use clear, descriptive commit messages:

**Format:**
```
Type: Short description (50 chars or less)

Longer explanation if needed (wrap at 72 chars).
Include motivation and contrast with previous behavior.

Fixes #123
```

**Types:**
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation only
- `style`: Code style changes (formatting)
- `refactor`: Code change that neither fixes bug nor adds feature
- `test`: Adding tests
- `chore`: Build process, tooling changes

**Examples:**
```
feat: Add pause/resume recording functionality

Implements pause and resume buttons in the notification
and main UI. Uses MediaRecorder pause() and resume() APIs
available in API 24+.

Fixes #42


fix: Correct audio source selection for internal audio

Internal audio was using MIC source instead of DEFAULT.
Changed to use AudioSource.DEFAULT for internal audio
on Android 10+.

Fixes #56


docs: Update README with new build instructions

Added section on gradle wrapper and updated
dependency versions.
```

#### Pull Request Process

1. **Update documentation**
   - Update README if needed
   - Update API.md for API changes
   - Add/update code comments

2. **Create pull request**
   - Describe what changed and why
   - Reference related issues
   - Include testing performed
   - Add screenshots for UI changes

**Pull Request Template:**
```markdown
## Description
Brief description of changes

## Related Issue
Fixes #123

## Changes Made
- Added feature X
- Modified component Y
- Refactored class Z

## Testing
- [ ] Built successfully
- [ ] Tested on physical device
- [ ] Verified no regressions
- [ ] All new code has appropriate comments

## Screenshots
(if applicable)

## Checklist
- [ ] Code follows project style guidelines
- [ ] Documentation updated
- [ ] No new warnings
- [ ] Tested on Android 10+
```

3. **Respond to feedback**
   - Address review comments
   - Make requested changes
   - Update PR description if scope changes

4. **Wait for approval**
   - At least one approval required
   - All checks must pass
   - Merge conflicts resolved

### Documentation Contributions

Documentation improvements are welcome!

**Areas to improve:**
- README clarity
- API documentation
- Code comments
- Tutorial content
- FAQ additions

**Process:**
1. Identify what needs improvement
2. Make changes in markdown files
3. Submit PR with documentation changes
4. No code testing needed for doc-only changes

### Translation Contributions

Currently not supported, but may be added in future:
- App strings (strings.xml)
- Documentation translations

## Development Setup

### Prerequisites
- Android Studio Arctic Fox or newer
- JDK 11+
- Android SDK with API 29+
- Physical device for testing

### Building from Source

```bash
# Clone repository
git clone https://github.com/techdiwas/screen-recorder-.git
cd screen-recorder-

# Build
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug

# Run tests (when added)
./gradlew test
```

### Project Structure

```
screen-recorder-/
├── app/
│   ├── src/main/
│   │   ├── java/com/techdiwas/screenrecorder/
│   │   │   ├── MainActivity.kt           # Main activity
│   │   │   └── ScreenRecorderService.kt  # Recording service
│   │   ├── res/                          # Resources
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── docs/
├── README.md
└── build.gradle.kts
```

## Communication

- **GitHub Issues**: Bug reports and feature requests
- **Pull Requests**: Code contributions and discussions
- **Discussions**: General questions and ideas

## Recognition

Contributors will be:
- Listed in CONTRIBUTORS.md (if added)
- Mentioned in release notes for their contributions
- Co-authors in commit messages (if applicable)

## License

By contributing, you agree that your contributions will be licensed under the MIT License.

## Questions?

- Review existing documentation
- Check FAQ.md
- Open a discussion on GitHub
- Ask in your pull request

---

Thank you for contributing to Screen Recorder! 🎉
