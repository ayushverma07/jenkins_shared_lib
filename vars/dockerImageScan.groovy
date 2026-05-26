def call(String project, String ImageTag, String hubUser){
    
    sh """   
        # 1. Create a workspace-isolated temp folder (which maps directly to your 13GB free root drive)
        mkdir -p .trivytmp
        
        # 2. Force the Linux shell to route all extraction files here instead of the 2GB /tmp RAM limit
        export TMPDIR=\$(pwd)/.trivytmp
        
        # 3. Execute the scan using local caching
        trivy image --cache-dir .trivycache/ ${hubUser}/${project}:${ImageTag} > scan.txt
        cat scan.txt
    """
}

// def call(String aws_account_id, String region, String ecr_repoName){
    
//     sh """
//     trivy image ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repoName}:latest > scan.txt
//     cat scan.txt
//     """
// }
