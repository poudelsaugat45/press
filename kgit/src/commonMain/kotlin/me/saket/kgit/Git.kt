package me.saket.kgit

interface Git {
  fun repository(
    path: String,
    sshKey: SshPrivateKey,
    remoteSshUrl: String,
    userConfig: GitConfig = GitConfig()
  ): GitRepository

  companion object
}

class RealGit : Git {
  override fun repository(
    path: String,
    sshKey: SshPrivateKey,
    remoteSshUrl: String,
    userConfig: GitConfig
  ): GitRepository = RealGitRepository(path, userConfig, GitRemote("origin", remoteSshUrl), sshKey)
}

expect fun Git.Companion.isKnownError(e: Throwable): Boolean
