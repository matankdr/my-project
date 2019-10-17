import sbtrelease.ReleaseStateTransformations._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.acme",
      scalaVersion := "2.12.6"
    )),
    name := "my-project",
    mainClass in Compile := Some("com.acme.StubMain")
  ).enablePlugins(JavaAppPackaging)

// remove below 3 lines if you don't have any Docker registry
//val dwDockerRegistry = "put-your-docker-registry-here"
//dockerRepository := Some(dwDockerRegistry)
//dockerBaseImage := s"$dwDockerRegistry/msi-base-image:latest"
/*
releaseProcess := Seq.empty[ReleaseStep]

releaseProcess ++= (if (sys.env.contains("RELEASE_VERSION_BUMP"))
                      Seq[ReleaseStep](
                        checkSnapshotDependencies,
                        inquireVersions,
                        setReleaseVersion,
                        commitReleaseVersion,
                        tagRelease
                      )
                    else Seq.empty[ReleaseStep])

releaseProcess ++= (if (sys.env.contains("RELEASE_PUBLISH"))
                      Seq[ReleaseStep](inquireVersions, setNextVersion, commitNextVersion)
                    else Seq.empty[ReleaseStep])

*/

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  setNextVersion,
  commitNextVersion,
  tagRelease,
)
